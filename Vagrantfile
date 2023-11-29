Vagrant.configure("2") do |config|
  config.vm.box = "bento/ubuntu-20.04"
  # Rediriger le port 8080 de la VM vers le port 8080 de l'hôte
    config.vm.network "forwarded_port", guest: 8080, host: 8080

  # Provision Docker
  config.vm.provision "docker"

  # Provision Docker Compose si nécessaire
  config.vm.provision "docker_compose"

  # Provision avec un script pour construire et démarrer l'application Docker
  config.vm.provision "shell", inline: <<-SHELL
    cd /vagrant # Supposons que votre Dockerfile est dans le répertoire partagé /vagrant
    docker build -t locmns:latest .
    docker run -d -p 8080:8080 --name locmns locmns:latest
  SHELL
end
