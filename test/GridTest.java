import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import com.example.ToneMatrix.AudioGenerator;
import com.example.ToneMatrix.MainActivity;
import com.example.ToneMatrix.Notes;

/**
 * Created by veritoff on 3/30/14.
 */
public class GridTest extends ActivityUnitTestCase<MainActivity>{

    public GridTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    @SmallTest
    public void testNotes() {
        Notes notes = new Notes(8000);
        AudioGenerator generateNotes = new AudioGenerator(8000);
        double[] array = new double[2600];
        int pointer;

        double[] temp = generateNotes.getSinWave(2400, 8000, 523.25);
        for (int i = 0; i < temp.length; i++)
            array[i] = temp[i];
        pointer = temp.length;
        temp = generateNotes.getSinWave(200, 8000, 0);
        for (int i = 0; i < temp.length; i++)
            array[i + pointer] = temp[i];

        assertEquals(notes.getUpperPhrase("C"), array);
    }

}
