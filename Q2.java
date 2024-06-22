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

public class Mahmued_2135209_Q2 {
    // The window handle
    private long window;
    private float translateX = 0.0f;
    private float translateY = 0.0f;
    private float translateZ = 0.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;
    private float rotationX = 0.0f;
    private float rotationY = 0.0f;

    public static void main(String[] args) {
        new Mahmued_2135209_Q2().run();
    }

    private void loop() {
        GL.createCapabilities();

        // Enable depth test
        glEnable(GL_DEPTH_TEST);

        // Set the clear color to black
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            // Handle key inputs for translation and scaling
            handleInput();

            glPushMatrix();

            // Apply translation and scaling
            glTranslatef(translateX, translateY, translateZ);
            glScalef(scaleX, scaleY, scaleZ);

            // Apply rotation
            glRotatef(rotationX, 1.0f, 0.0f, 0.0f);
            glRotatef(rotationY, 0.0f, 1.0f, 0.0f);

            drawBox();

            glPopMatrix();

            glfwSwapBuffers(window); // swap the color buffers

            glfwPollEvents();
        }
    }

    private void handleInput() {
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) translateY += 0.01f;
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) translateY -= 0.01f;
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) translateX -= 0.01f;
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) translateX += 0.01f;
        if (glfwGetKey(window, GLFW_KEY_U) == GLFW_PRESS) scaleY += 0.01f;
        if (glfwGetKey(window, GLFW_KEY_J) == GLFW_PRESS) scaleY -= 0.01f;
        if (glfwGetKey(window, GLFW_KEY_K) == GLFW_PRESS) scaleX += 0.01f;
        if (glfwGetKey(window, GLFW_KEY_H) == GLFW_PRESS) scaleX -= 0.01f;
        if (glfwGetKey(window, GLFW_KEY_M) == GLFW_PRESS) scaleZ += 0.01f;
        if (glfwGetKey(window, GLFW_KEY_N) == GLFW_PRESS) scaleZ -= 0.01f;
        if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS) rotationX -= 2.0f;
        if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS) rotationX += 2.0f;
        if (glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS) rotationY -= 2.0f;
        if (glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS) rotationY += 2.0f;
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

    private void drawBox() {
        glBegin(GL_QUADS);

        // Front face
        glColor3f(1.0f, 0.0f, 0.0f);
        glVertex3f(-0.5f, -0.5f, 0.5f);
        glVertex3f(0.5f, -0.5f, 0.5f);
        glVertex3f(0.5f, 0.5f, 0.5f);
        glVertex3f(-0.5f, 0.5f, 0.5f);

        // Back face
        glColor3f(0.0f, 1.0f, 0.0f);
        glVertex3f(-0.5f, -0.5f, -0.5f);
        glVertex3f(0.5f, -0.5f, -0.5f);
        glVertex3f(0.5f, 0.5f, -0.5f);
        glVertex3f(-0.5f, 0.5f, -0.5f);

        // Left face
        glColor3f(0.0f, 0.0f, 1.0f);
        glVertex3f(-0.5f, -0.5f, -0.5f);
        glVertex3f(-0.5f, -0.5f, 0.5f);
        glVertex3f(-0.5f, 0.5f, 0.5f);
        glVertex3f(-0.5f, 0.5f, -0.5f);

        // Right face
        glColor3f(1.0f, 1.0f, 0.0f);
        glVertex3f(0.5f, -0.5f, -0.5f);
        glVertex3f(0.5f, -0.5f, 0.5f);
        glVertex3f(0.5f, 0.5f, 0.5f);
        glVertex3f(0.5f, 0.5f, -0.5f);

        // Top face
        glColor3f(1.0f, 0.0f, 1.0f);
        glVertex3f(-0.5f, 0.5f, -0.5f);
        glVertex3f(0.5f, 0.5f, -0.5f);
        glVertex3f(0.5f, 0.5f, 0.5f);
        glVertex3f(-0.5f, 0.5f, 0.5f);

        // Bottom face
        glColor3f(0.0f, 1.0f, 1.0f);
        glVertex3f(-0.5f, -0.5f, -0.5f);
        glVertex3f(0.5f, -0.5f, -0.5f);
        glVertex3f(0.5f, -0.5f, 0.5f);
        glVertex3f(-0.5f, -0.5f, 0.5f);

        glEnd();
    }
}
