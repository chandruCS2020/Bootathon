import static com.teamdev.jxbrowser.engine.RenderingMode.*;
 
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;
 
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**...*/
public final class SwingDemo {
public static void main(String[] args) {
Engine engine = Engine.newInstance(EngineOptions.newBuilder(OFF_SCREEN).licenseKey("6P830J66YB9BBP8JCNNXXZM6OJDI3K9HFCQOEG6V93XPDQDHASR9P22TQUJQWQV9BL37").build());
Browser browser = engine.newBrowser();
 
SwingUtilities.invokeLater(() -> {
  BrowserView view = BrowserView.newInstance(browser);
 
  JFrame frame = new JFrame("Swing BrowserView");
  frame.add(view, BorderLayout.CENTER);
  frame.setSize(700, 500);
  frame.setVisible(true);
 
  browser.navigation().loadUrl("https://html5test.com");
});
}
}