# PasteProj
Initially, it was an usual rest service - https://gitlab.com/shibkov.k/pastebox, but I finished it by adding pages, validating, database and others so this’s what we have. You can create your own paste, specify: text, expiration time, public or unlisted status, the last 20 public pastes appear on the main page. After creating a paste, it has own hash by which you can access the paste details as text, id, hash and others. Minimum value for expiration time is 15 seconds, maximum 300. Every 10 seconds the program’s checking for expired pastes and delete expired. At the end we have a few tests for almost all the methods in the service, except the schedulded. 
#### Pages: homepage, createpage, getpage, nullgetpage
### Techonology stack: 
Spring Framework(Core, Web MVC, Data, Boot) \
Apache Tomcar Server \
JDBC, JPA(Hibernate) \
Hibernate Validator, ORM \
Lombok, JSON Jackson \
Bootstrap, Thymeleaf \
JUnit, Mockito \
Git
