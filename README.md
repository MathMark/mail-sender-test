# Power-MS

Sends letters to a list of set emails. 

Before start, find persistence.xml and use these properties:

\<property name="hibernate.hbm2ddl.auto" value="create"/></br>
\<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/></br>
\<property name="hibernate.connection.url" value="jdbc:h2:mem:main"/></br>
\<property name="hibernate.show_sql" value="false"/></br>

<b>How to use:</b>
1. Add a gmail account;
2. Create an email template that includes header and body;
3. Create a .csv file with list of emails that should contain three colums: Email, First Name, Last Name;
4. Create a new campaign with necessary template, account, list of emails, and delay.

Note that there are features that have not been developed yet.
