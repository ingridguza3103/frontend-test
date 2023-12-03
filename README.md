# ASE-Marketplace
ASE PROJECT

How to execute the project:
1. RUN mvn clean install
2. RUN IN TERMINAL: docker-compose --build 

How to connect to the database:
1. EXECUTE IN TERMINAL: docker exec -it marketplace-postgres-1 psql -U myuser -d mydatabase 
2. EXECUTE: \c mydatabase
3. EXECUTE: \d to see the relations
4: QUERYING: e. g. SELECT * FROM users; (note ; at the end is important)
