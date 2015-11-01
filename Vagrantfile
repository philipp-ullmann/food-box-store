# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = 'parallels/ubuntu-14.04'
  config.vm.network 'forwarded_port', guest: 3000, host: 3000
  config.vm.provision :shell, path: 'provision.sh'

  config.vm.provider 'parallels' do |vb|
    vb.name               = 'food-box-store'
    vb.memory             = 2072
    vb.cpus               = 1
    vb.update_guest_tools = true
  end
end
