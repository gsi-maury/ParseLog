# ParseLog

ParseLog is a JAVA library for read and filter a log file with a specific format.

#### Log File Format
Every line of the file follow the next structure:
1. Each line starts with F or D.
2. If F, this is a "format" line and two formats can be provided (1 or 2).
3. If D, this is a "data" line whose format is defined by the closest above format line.
4. If "F1" is specified, you can expect these fields separated with a comma:\
    a. D defining a data line\
    b. Name and surname\
    c. City\
    d. ID formatted as "12345678Z"
5. If "F2" is specified, you can expect these fields separated with space-semicolon-space:\
    a. D defining a data line\
    b. Name and surname\
    c. City\
    d. ID formatted as "12345678-Z"
    
#### Parameters
1. FILE: Location of file log.
2. NAME: Name to filter in log file.
3. CITY: City to filter in log file.
4. ID: Id to filter in log file.

#### Installation
1. Clone or download the project.
2. Build project
```bash
    mvn package
```
3. Use the .jar

#### Usages
##### Without filters
###### Input

```bash
java -jar test-1.0-SNAPSHOT.jar FILE=test.txt
```

###### Output
```bash
D Erica Burns,BARCELONA,93654902Y
D Lucy Mcgee,LONDON,51011156P
D Mitchell Newton,SAN FRANCISCO,25384390A
D Margarita Richards,LAS VEGAS,09877359D
D Rhonda Hopkins,SAN FRANCISCO,54315871Z
...
```
##### Single filter
###### Input

```bash
java -jar test-1.0-SNAPSHOT.jar FILE=test.txt CITY=LONDON
```

##### Output
```bash
D Lucy Mcgee,LONDON,51011156P
D Hilda Caldwell,LONDON,61682270L
D Irene Owen,LONDON,15015516N
D Dwight Roy,LONDON,87179151C
```

##### Multiple filter
###### Input

```bash
java -jar test-1.0-SNAPSHOT.jar FILE=test.txt CITY=LONDON ID=15015516N
```

##### Output
```bash
D Irene Owen,LONDON,15015516N
```