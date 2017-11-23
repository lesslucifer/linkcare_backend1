cd ./trunk/implementation
mvn clean install
cd ../../build
rm -f clinic-api.war
cp ../trunk/implementation/core-systems/clinic-api/target/clinic-api.war ./
scp clinic-api.war root@103.200.23.195:/root/downloads/
ssh root@103.200.23.195 "~/scripts/deploy_api.sh"