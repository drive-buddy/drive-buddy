## How to use DBHelper class ?
Here are examples for each method:
```kt
// you specify the ID of the ride first and then the id of the passenger to add
// in the database, the ride will store an array of document ids for passengers
db.addPassengerToRide("UQTVX6f7aJxbPdb0KNRp", "Q7CrX7eKIQWiGKSeWFod")
```

```kt
db.getOrderPassengers("UQTVX6f7aJxbPdb0KNRp") { response ->
            // response is Any, so we need to explicitly cast it to a list of references
            val passengers: ArrayList<DocumentReference> = response as ArrayList<DocumentReference>

            // with each reference do something
            for (passenger in passengers) {
                Log.w("DBHelper", passenger.toString())
            }
        }
```
Now an example of creating a document in the database
```kt
// addOrder and addUser work the same way, just require different data (based on db schema)
        // both require a hashmap
        db.addOrder(hashMapOf(
            "driver" to "Ghita",
            "carModel" to "Dacia Logan"
        ))
```
There are also methods that require a callback. A callback is an argument to the method, which is a function. It will be attached subscribed to an event. After the event is finished, the callback function is called with arguments like data retrieved from the database (in our case). It is similar to Promises callbacks in javascript: `.then(data => {...})`. The `data => {...}` part is a callback in the form of an anonymous function.

Here is an example with `DBHelper`:
```kt
// first argument is a callback with a QuerySnapshot argument
        db.getUsers({ result ->
            // you access QuerySnapshot elements with .documents
            // add each element's data with .data
            for (document in result.documents) {
                Log.w("DBHelper", document.data.toString())
            }
        },
        carModel = "Dacia Logan")
```
And that's everything you need to know, because other methods are similar. If you have any question, ping me.
