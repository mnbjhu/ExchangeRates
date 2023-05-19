## Currency Converter

### Config
This application relies on the openexchangerates.org api to fetch information about the current exchange rates. 
You will need an app ID inorder to authenticate the requests, which can be obtained from https://openexchangerates.org/
Once you have an app key, create a file called 'local.properties' in 'src/main/resources' with the following contents:
```properties
app_id=*YOUR_APP_ID*
```

### Running
The application has been configured with gradle and can be run with the 'run' task.
This will run the server locally at 'http://localhost:8080/app'.

### Testing
The test suit can be run with the 'test' task the results will be placed at 'build/reports/tests/test/packages/com.example.html'