import ru.REStudios.v8.graphics.Scene;
import ru.REStudios.v8.graphics.Shader;
import ru.REStudios.v8.graphics.Window;
import ru.REStudios.v8.utils.Log4jPrintStream;

import java.io.IOException;
import java.util.Objects;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class Test {


    public static void main(String[] args) {
        Log4jPrintStream.setupPrints();
        Window window = Window.setup(1000,800,false,"hi");
        Scene scene = new Scene() {
            TestGameObject gameObject;
            Shader shader;
            CameraObject camera;


            @Override
            public void update(double dt) {
                super.update(dt);
                gameObject.update(dt);
            }


            @Override
            public void render() {
                ///shader.use();
                //shader.uploadMat4f("uProjection", Objects.requireNonNull(findCamera()).getProjectionMatrix());
                //shader.uploadMat4f("uView", Objects.requireNonNull(findCamera()).getViewMatrix());
                gameObject.render();

            }
            @Override
            public void init() throws IOException {
                gameObject = addGameObject(new TestGameObject());
                //shader = new Shader("default.vert","default.frag");
                //shader.compile();
                camera = addGameObject(new CameraObject());
            }
        };
        Window.setScene(scene);
        window.run();
        window.waitFor();
        window.destroy();
        System.out.println("Destroyed!");
    }


}
