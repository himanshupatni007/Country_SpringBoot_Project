I have attached a document as well for the request and response of all following urls.Please go through once for better clarity-
__________________________________________________________________________________________________________________________________________________________________________

"name":"Himanshu",
"password":"himanshu@123"
for generating jwtToken name and password are as above.

For getting all records from database url are as follows-
http://localhost:9090/api/country/?pageNumber=2&pageSize=4&sortBy=countryName

I have implemented pagination while fetching all records.For more idea please go through serviceImpl.

For getting a particular record url-Get mapping
http://localhost:9090/api/country/1
Here 1 is the country id.

For updating a particular record url-Put mapping
http://localhost:9090/api/country/1

For creating new record-Post mapping
http://localhost:9090/api/country/

For deleting a particular record-Delete mapping
http://localhost:9090/api/country/2
Here 2 is the country id.

