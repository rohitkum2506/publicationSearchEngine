echo $1
if [ $1 = "linux" ]
then
echo “here”
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.2.2.zip
sha1sum elasticsearch-5.2.2.zip 
sudo apt-get install unzip
sudo apt-get install make
sudo apt-get install maven
unzip elasticsearch-5.2.2.zip
cd elasticsearch-5.2.2/

sudo /bin/systemctl daemon-reload
sudo /bin/systemctl enable elasticsearch.service


sudo /bin/systemctl start elasticsearch.service &
echo "starting elastic search as a background process"
make noTest
make run
echo “runnable file present at location ‘MSD’ from project root”
fi

if [ $1 = "mac" ]
then
echo "First arg: $1"
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
brew update
brew install make
brew install maven
brew install elasticsearch
sudo /bin/systemctl stop elasticsearch.service



sudo mkdir /usr/local/var/elasticData
sudo /bin/systemctl stop elasticsearch.service &
sudo /bin/systemctl start elasticsearch.service &

make noTest
make run

echo "starting elastic search as a background process"
echo “runnable .dmg file present at location ‘MSD’ from project root.”
fi



