# Makefile for MapReduce Page Rank project.

# Customize these paths for your environment.
# -----------------------------------------------------------

#TYPE=coarse

# -----------------------------------------------------------

# Compiles code and builds jar (with dependencies).


build:	
	cd MSD;mvn clean package

test:	
	cd MSD;mvn test

default:
	cd MSD;mvn clean package

noTest:
	cd MSD;mvn package -Dmaven.test.skip=true

backup_es:
	rm -rf elasticsearch_backup;mkdir elasticsearch_backup

restore:
	curl -XPOST localhost:9200/_snapshot/public_backup/snapshot_1/_restore -d '{"indices":"dblp-test","ignore_unavailable":true}'	

mac:
	sh installElastic.sh mac

linux:
	sh installElastic.sh linux

run:
	cd MSD;mvn jfx:native -DskipTests;cp -r target/jfx/native/ .

index:
	cd MSD;java -Xmx16000M -DentityExpansionLimit=2500000 -cp target/SearchEnginer-0.1-jar-with-dependencies.jar team1.searchengine.frontend.FrontEnd &

runapp:
	chmod +x launch.sh;./launch.sh

deploy:
	cd MSD;aws s3 cp target/SearchEnginer-0.1-jar-with-dependencies.jar s3://rohitpagerank/searchEngine.jar
