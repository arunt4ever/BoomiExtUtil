# BoomiExtUtil
## Kotlin in Boomi - Boomi Extended Util library

This library is completely written in Kotlin for Boomi Script use cases.

### How to install this Library in your environment
Below are the steps you have to follow to use this in your Boomi Environment;
1. Download the latest release .jar file from this link [Latest Release](https://github.com/arunt4ever/BoomiExtUtil/releases)
2. Upload this jar file in your Boomi account [Upload in Boomi](https://help.boomi.com/bundle/integration/page/t-atm-Uploading_a_custom_file.html)
3. Create Custom Library component in Boomi [Create Custom Library Component](https://help.boomi.com/bundle/integration/page/t-atm-Creating_a_Custom_Library_Component.html)
4. Select Custom Library Type -> Scripting.
5. Search and Add the .jar file which was previously uploaded in your account.
6. Deploy this component to your environment.
7. Create a new process and write a Groovy custom script in data process shape in Boomi and then test the process on the same environment you have deployed this BoomiExtUtil.

#### Simple Usage Code:
````java
import java.util.Properties;
import java.io.InputStream;
import com.arunzlair.boomiextutil.Simple;
import com.boomi.execution.ExecutionUtil;

logger = ExecutionUtil.getBaseLogger();


Simple simple = new Simple()
logger.info(simple.getSimpleMessage());

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

    dataContext.storeStream(is, props);
}
````


#### JSON Schema Validation:

Reference Links:
1. [JSON-Schema](http://json-schema.org/)
2. [everit-org/json-schema](https://github.com/everit-org/json-schema)

Tested with Draft4 and should support Draft 6 and 7 as well. 

````java
import java.util.Properties;
import java.io.InputStream;
import com.arunzlair.boomiextutil.JSONValidation;
import com.boomi.execution.ExecutionUtil;
import groovy.transform.Field

logger = ExecutionUtil.getBaseLogger();

@Field
JSONValidation jsonVal = new JSONValidation()

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    String jsonSchema = props.getProperty("document.dynamic.userdefined.DDP_JSON_SCHEMA");
    String jsonData = props.getProperty("document.dynamic.userdefined.DDP_JSON_FILE");
    
    Boolean result = jsonVal.isValid(jsonSchema, jsonData)
    
    // default delimiter is "; "
    String message1 = jsonVal.getValidationMsg()
    logger.info(message1);
    
    // newline "\n" char
    String message2 = jsonVal.getValidationMsg("\n")
    logger.info(message2);
    
    // Validation Message in JSON format
    String message3 = jsonVal.getValidationMsgJSON()
    logger.info(message3);

    dataContext.storeStream(is, props);
}
````


