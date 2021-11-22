# TravelAPIAssignmentKLM
This is the assignmennt from KLM.

Here I will list the tasks that I have done and give a bit of explainations for each task
As I don't really have experience with front end and gradle, I have discussed with Dima, he said that I can only do the backend part without gradle.
So I created a new spring boot project with maven.

1. Consume the mocked services from the service mock project.
   I called the mock api and get data in my application
   
2. The authentication is on application level, so make sure the user interface is not bothered with authentication.
   I called the mock api internally, so for my application, it won't ask auth again.
   
3. The mock is a bit inefficient, the fare result only returns origin/destination codes without any additional details.
   When handling this request for your user interface retrieve details for origin, destination and the actual fare and 
   return a single result to your user interface. Doing this sequentially would not be very efficient so perform these 
   service calls in parallel.
   I achieved this with the endpoint: http://localhost:9090/fares/{origin}/{distination} ,

4. Add statistics for your backend

  Total number of requests processed 
  Total number of requests resulted in an OK response
  Total number of requests resulted in a 4xx response
  Total number of requests resulted in a 5xx response
  Average response time of all requests
  Min response time of all requests
  Max response time of all requests
  
  To not only store this data expose this information in a new restful endpoint as JSON and create a new dashboard to visualize this info.
  For this task, I used spring boot actuator to get metrics data, and then call http://localhost:9090/actuator/metrics/http.server.requests?tag=status:{status code}
  to get the numbers for status ok (I only called 200), status 4xx(only called 404, here I also consider to add 401 and 403, but for my app, there is no auth needed) 
  or status 5xx(only called 500). 
  I did not write them together as one json, because I got counting issue. So I write them respectively with different endpoints: 
  http://localhost:9090/statistics/status/all,
  http://localhost:9090/statistics/status/ok,
  http://localhost:9090/statistics/status/4xx,
  http://localhost:9090/statistics/status/5xx
  To the Min response time of all requests, I don't really find this number in metrics, so I am also wondering how can I achieve this. If I got a chance to the
  next interview, I really woule like to ask how can I do this. 

  For the dash board, I was trying to create it using combination of actuator and prometheus. I have added configurations to application.properties and 
  peometheus.yml. But I got issue to run prometheus on docker. So I cannot really visualize the data there. 
  
5. Make the application configurable
  I have a configuration file in the config package
  
6. Bonus points!

  * Create a new page where you can see a list of all airports (and be able to search on them) and paginate the results.
    I have added the pagination in my application
    http://localhost:9090/airports?page=5&size=2 ,
    
  * The mock service does not provide any form or sorting, find a way to add sorting without having to change the original mock
    I have added sorting function to the api whithout changing mock api. I implement the sorting the JSON Array on the data that I got from mock api. 
    http://localhost:9090/airports?page=5&size=2&orderby=code desc ,

  * Provide a thread name that looks like 'async-task-{thread_id}' for all threads that perform async tasks.
  * Provide a unique ID to each request for tracking purposes and have every log line include it before the message.
    For this two tasks I don't rellay know how to do that, I tried to create unique ID, but failed. If I got a chance to the
    next interview, I really woule like to ask how can I do this. 

  * Show us your knowledge of java up to and including the latest released version!
    I used Java 8 for this assignment
  
  
  
  
  
  
  
