import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit ;

class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    void mainDuration_22_SecondsLess(){
        try{
            Main.main(new String[]{});
        } catch (Exception e){
            e. printStackTrace();
        }
    }
}