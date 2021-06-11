Vaccine App Enhancements
========================

If there were no time constraints, I would like to develop this as an analytic engine hosted on a http server. 

* This service along with prioritizing the population for vaccination, would also analyze the the status of the vaccination drive i.e more vaccinated areas, sparsely vaccinated areas, majorly vaccinated age group etc.

* The service would also update the population list timely by the population's vaccination status. The sync can happen on a regular basis at a certain point of day.

* Users can be sent notifications and timely reminders about the vaccination appointment booked.

* Users can also be notified about the current queue count at the vaccination counter.

* The service can be developed using SpringBoot, with Rest endpoints like below to upload Population data and vaccination centers-info files.
 
* There could be a notification service, which is scheduled to send notifications to users about the vaccination centers near to them, about the booked appointments, current population load at the vaccination center.

* A dynamic configuration service, to update the population data and vaccination centers with the latest data.
* An analytic engine which would run timely analytics and provides insights about vaccination status to the user.

#####Sample Rest endpoints#####
```
 - POST ("/population") @RequestParam ("file") MultipartFile file
 - POST ("/vaccineCentre") @RequestParam ("file") MultipartFile file 
 - GET ("/) @RequestParam("age") String sortedBy, 
 Request params can be any field through which the sorting can be performed. This is to facilitate advanced grouping, not only based on age.
```
