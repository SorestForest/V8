import ru.REStudios.v8.graphics.Scene;
import ru.REStudios.v8.graphics.Window;
import ru.REStudios.v8.utils.Log4jPrintStream;

import java.io.IOException;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class Test {


    public static void main(String[] args) {
        Log4jPrintStream.setupPrints();
        Window window = Window.setup(600,600,false,"hi");
        Scene scene = new Scene() {

            TestGameObject gameObject;

            @Override
            public void update(double dt) {
                gameObject.update(dt);
            }

            @Override
            public void render() {
                gameObject.render();
            }

            @Override
            public void init() throws IOException {
                System.out.println("Loading!");
                gameObject = new TestGameObject();
                gameObject.init();
            }
        };
        Window.setScene(scene);
        window.run();
        while (!window.shouldClose()){
            int i = 0;
            i += 1;
            ;
        }
        window.destroy();
        System.out.println("Destroyed!");
    }


}
