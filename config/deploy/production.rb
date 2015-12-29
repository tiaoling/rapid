set :stage, :production

set :profile, "production"

set :deploy_to, "/data/www/price-services"

set :jar_pid, "#{shared_path}/tmp/pids/application.pid"

set :server_name, "10.230.3.181"

set :branch, "master"

set :default_env, {
  'PATH' => 'PATH=/deploy/soft/jdk1.8.0_66/bin:$PATH'
}

server fetch(:server_name), user: 'deploy', roles: %w{web app}
