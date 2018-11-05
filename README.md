# Reference Minder

Reference Minder is a reference management web application. It supports saving a variety of information about
a reference including title, date, date accessed, authors and reference type. Additionally, references can be tagged and
these tags enable the grouping of similar references.


### Installing and Running

Installing frontend build tools:

````
npm install
````

Running webpack and rebuilding when files change:

````
npm run auto-build

````

Installing dependencies and building:

````
mvn clean install
````

Running:

````
mvn spring-boot:run
````

### To-Do

- [x] View listing of references
- [x] Save Reference
- [x] Tag Listing
- [x] Delete Reference
- [x] Update Reference
- [x] Reference Form Modal
- [x] Improve Design
- [x] Re-run MapReduce function when the data changes