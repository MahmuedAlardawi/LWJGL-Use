import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Mahmued_2135209_Q1 {  // Replace "template_2D" with your file name
    // The window handle
    private long window;

    public static void main(String[] args) {new Mahmued_2135209_Q1().run();}  // Replace "template_2D" with your file name

    private void loop() {
        GL.createCapabilities();

        float angle = 200.0f;

        // Enable 2D texturing
        glEnable(GL_TEXTURE_2D);

        // Set the clear color to black
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glPushMatrix();

            // Rotate shape
            glRotatef(angle, 0.0f, 1.0f, 0.0f);

            // Continually rotate the shape on the Y-axis
            angle += 0.1f;

            // Draw the letter "M"
            glBegin(GL_TRIANGLE_STRIP);
            {
                glColor3f(1.0f, 0.0f, 0.0f);  // Red color

                // Define vertices for the letter "M"
                glVertex2f(-0.5f, -0.8f);
                glVertex2f(-0.4f, -0.8f);

                glVertex2f(-0.5f, 0.8f);
                glVertex2f(-0.4f, 0.8f);

                glVertex2f(-0.05f, -0.8f);
                glVertex2f(0.05f, -0.8f);

                glVertex2f(0.4f, 0.8f);
                glVertex2f(0.5f, 0.8f);

                glVertex2f(0.4f, -0.8f);
                glVertex2f(0.5f, -0.8f);
            }
            glEnd();

            glPopMatrix();

            glfwSwapBuffers(window); // swap the color buffers

            glfwPollEvents();
        }
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1000, 600, "Your_ID_Here", NULL, NULL);  // Change the title to your ID
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }
}
