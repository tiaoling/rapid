require 'capistrano-daemonize'

# config valid only for current version of Capistrano
lock '3.3.5'


set :application, 'price-service'
set :repo_url, 'git@git.rfdoa.cn:java/price-service.git'

set :scm, :git

set :version, "0.1.1"
set :jar_file, "price-service-#{fetch(:version)}.jar"


# Default value for :format is :pretty
set :format, :pretty

# Default value for :log_level is :debug
set :log_level, :debug

# Default value for :pty is false
set :pty, true

# Default value for :linked_files is []
# set :linked_files, fetch(:linked_files, []).push('src/main/resources/application.yml')

# Default value for linked_dirs is []
# set :linked_dirs, fetch(:linked_dirs, []).push('bin', 'log', 'tmp/pids', 'tmp/cache', 'tmp/sockets', 'vendor/bundle', 'public/system')

# Default value for default_env is {}
# set :default_env, { path: "/opt/ruby/bin:$PATH" }

set :default_env, {
  path: "/home/deploy/soft/jdk1.8.0_66/bin:$PATH"
}


# Default value for keep_releases is 5
set :keep_releases, 5

namespace :deploy do

  task :start do
     on roles :app do
       within "#{fetch(:deploy_to)}" do
         unless test("[ -f #{fetch(:jar_pid)} ]")
           info ">>>> starting "
           execute :nohup, :java, "-jar -Dspring.profiles.active=#{fetch(:profile)} #{fetch(:jar_file)} >price-nohup.log 2>&1 & sleep 1"
           # daemonize "java -jar #{fetch(:jar_file)}",as: 'myworker', callbacks: true, role: :worker
           # execute :sh,  "#{fetch(:deploy_to)}/current/config/deploy.sh  && sleep 1"
         else
           error ">>>> already started"
         end
       end
     end
  end

  task :stop do
     on roles :app do
       within "#{fetch(:deploy_to)}" do
         if test("[ -f #{fetch(:jar_pid)} ]")
           info ">>>> stopping  "
           execute :kill, "-9 `cat #{fetch(:jar_pid)}`"
           execute :rm, "#{fetch(:jar_pid)}"
         else
           error ">>>> can not stop, there is no live app"
         end
       end
     end
  end

  task :restart do
     invoke :"deploy:stop"
     invoke :"deploy:start"
  end

  task :build do
     on roles :app do
       within current_path do
         execute :sh, "gradlew build -xTest"
       end
     end
  end

  task :copy_to_target do
     on roles :app do
       within current_path do
         execute :cp, "build/libs/#{fetch(:jar_file)} #{fetch(:deploy_to)}"
       end
     end
  end

  task :rebuild do
     invoke :"deploy:build"
     invoke :"deploy:copy_to_target"
     invoke :"deploy:stop"
     invoke :"deploy:start"
  end


end

after "deploy:publishing", "deploy:rebuild"

