# CityVenueViewer
Native Android application written in Java implemented using MVP architecture.
The app makes network calls to Foursquare's open API and displays data in a RecyclerView.
The API is returning venue data that is requested by city. Cities are stored in the app in
a Room database and are populated with data from a static function in the City entity class.
Adding more cities to the app can be done by extending the static function in the City entity
class or implementing another method of populating the City table

## Dependencies

### Room

### Gson

### Retrofit

### Dagger2

### Glide

### Mockito

### Robolectric