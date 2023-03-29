# 2023-java-t03

## Build Path

### Module Path
- byte-buddy-1.12.16
- commons-lang3-3.12.0  [Download Apache Commons Lang jar here](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.12.0)
- jakarta.persistence.2.2.3
- javafx.base
- javafx.controls
- javafx.fxml
- javafx.graphics
- jbcrypt-0.4  [Download bcrypt jar here](https://mvnrepository.com/artifact/org.mindrot/jbcrypt/0.4)
- mockito-core-4.8.0
- mockito-junit-jupiter-4.8.0
- objenesis

### Class Path
- eclipselink
- jakarta.persistence.source_2.2.3
- mysql-connector-java-8.0.27
- org.eclipse.persistence.jpa.modelgen_2.7.10
- org.eclipse.persistence.jpa.modelgen.source_2.7.10
- org.eclipse.persistence.jpars_2.7.10
- org.eclipse.persistence.jpars.source_2.7.10



## persistence.xml content
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
	xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
	<persistence-unit name="b2b_portal"
		transaction-type="RESOURCE_LOCAL">
		<provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
		<!-- <class>domein.CLASSNAME</class> goes here -->
		<class>domein.Bedrijf</class>
		<class>domein.User</class>
		<class>domein.Medewerker</class>
		<class>domein.Dienst</class>
		<class>domein.Transportdienst</class>
		<class>domein.Persoon</class>
		<class>domein.Contactpersoon</class>
		<class>domein.TrackTraceFormat</class>
		<class>domein.Bestelling</class>
		<class>domein.Product</class>
		<class>domein.BesteldProduct</class>
		<class>domein.Doos</class>
		<class>domein.Dimensie</class>
		<class>domein.Notificatie</class>
		<properties>
			<property name="javax.persistence.jdbc.url"
				value="jdbc:mysql://vichogent.be:40058/SDP2DBT03?serverTimezone=UTC" />
			<property name="javax.persistence.jdbc.user" value="SDP2T03" />
			<property name="javax.persistence.jdbc.driver"
				value="com.mysql.cj.jdbc.Driver" />
			<property name="javax.persistence.jdbc.password"
				value="@7PlyRUVHzP#1qG7Qm@f#dBTi" />
			<property
				name="javax.persistence.schema-generation.database.action"
				value="none" />
		</properties>
	</persistence-unit>
</persistence>
```

## Log-in demo accounts
- "emailail1@test.com", "paswoord"   -> Admin user bedrijf A
- "mag1@test.com", "paswoord"        -> Magazijnier bedrijf A
- "emailail4.bart@test.com", "paswoord" -> Admin user bedrijf B
- "mag2@test.com", "paswoord" -> Magazijnier bedrijf B