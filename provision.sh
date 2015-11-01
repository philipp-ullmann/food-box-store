#!/usr/bin/env bash

# Upgrade system
apt-get update
apt-get -y dist-upgrade
apt-get -y autoremove

# Install tools
apt-get install -y software-properties-common vim curl build-essential postgresql-9.3 postgresql-contrib-9.3 libpq-dev

# PostgreSQL
rm /etc/postgresql/9.3/main/pg_hba.conf
echo 'local all all trust' >> /etc/postgresql/9.3/main/pg_hba.conf
echo 'host all all 127.0.0.1/32 trust' >> /etc/postgresql/9.3/main/pg_hba.conf
echo 'host all all ::1/128 trust' >> /etc/postgresql/9.3/main/pg_hba.conf
service postgresql restart
su postgres -c "psql -c \"CREATE ROLE vagrant SUPERUSER LOGIN PASSWORD 'vagrant'\""
su postgres -c "createdb -E UTF8 -T template0 --locale=en_US.utf8 -O vagrant food_box_store"

# Java
add-apt-repository ppa:webupd8team/java
apt-get update
/usr/bin/debconf-set-selections <<< "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true"
apt-get install -y oracle-java8-installer
update-java-alternatives -s java-8-oracle

# Leiningen
curl https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein > /usr/local/bin/lein
chmod +x /usr/local/bin/lein

# Timezone
echo "Europe/Vienna" | tee /etc/timezone
dpkg-reconfigure --frontend noninteractive tzdata

# Vim
if [ ! -e "/home/vagrant/.vimrc" ]; then
sudo -u vagrant touch /home/vagrant/.vimrc
sudo -u vagrant cat > /home/vagrant/.vimrc <<EOF
syntax enable
filetype plugin indent on
:set number
:set expandtab
:set tabstop=2
:set shiftwidth=2
:set encoding=utf-8
:set fileencoding=utf-8
:set ruler
EOF
fi
