package mini_supermarket.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import mini_supermarket.GUI.component.SmoothIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Resource {
    public static URL getJarURL() {
        return Resource.class.getProtectionDomain().getCodeSource().getLocation();
    }

    public static Path getPathOutsideJAR() {
        try {
            return Paths.get(getJarURL().toURI()).getParent();
        } catch (URISyntaxException e) {
            Log.error(Resource.class, e.toString());
        }
        return null;
    }

    public static boolean exists(String relativePath, boolean insideJAR) {
        URL url = null;
        if (insideJAR)
            url = Resource.class.getResource("/" + relativePath);
        else {
            try {
                Path path = getPathOutsideJAR();
                if (path != null) {
                    path = path.resolve("resources" + relativePath);
                    url = path.toUri().toURL();
                }
            } catch (MalformedURLException e) {
                return false;
            }
        }
        return url != null;
    }

    public static URL getURLFromRoot(String relativePath, boolean insideJAR) {
        if (insideJAR)
            return Resource.class.getResource("/" + relativePath);
        try {
            Path path = getPathOutsideJAR();
            if (path != null) {
                path = path.resolve(relativePath);
                return path.toUri().toURL();
            }
            Log.error(Resource.class, "Can't get the path outside the JAR: " + relativePath);
        } catch (MalformedURLException e) {
            Log.error(Resource.class, e.toString());
        }
        return null;
    }

    public static InputStream getInputStreamFromRoot(String relativePath, boolean insideJAR) {
        if (insideJAR)
            return Resource.class.getResourceAsStream("/" + relativePath);
        try {
            Path path = getPathOutsideJAR();
            if (path != null) {
                path = path.resolve(relativePath);
                return Files.newInputStream(path);
            }
            Log.error(Resource.class, "Can't get the path outside the JAR: " + relativePath);
        } catch (IOException e) {
            Log.error(Resource.class, e.toString());
        }
        return null;
    }

    public static Path getPathFromRoot(String relativePath, boolean insideJAR) {
        try {
            URL url = getURLFromRoot(relativePath, insideJAR);
            if (url != null)
                return Paths.get(url.toURI());
            Log.error(Resource.class, "Can't get the URL from the root: " + relativePath);
        } catch (URISyntaxException e) {
            Log.error(Resource.class, e.toString());
        }
        return null;
    }

    public static URL getURLFromResource(String relativePath, boolean insideJAR) {
        if (insideJAR)
            return getURLFromRoot(relativePath, true);
        return getURLFromRoot("resources" + File.separator + relativePath, false);
    }

    public static InputStream getInputStreamFromResource(String relativePath, boolean insideJAR) {
        if (insideJAR)
            return getInputStreamFromRoot(relativePath, true);
        return getInputStreamFromRoot("resources" + File.separator + relativePath, false);
    }

    public static Path getPathFromResource(String relativePath, boolean insideJAR) {
        if (insideJAR)
            return getPathFromRoot(relativePath, true);
        return getPathFromRoot("resources" + File.separator + relativePath, false);
    }

    public static String getResourcePath(String relativePath, boolean insideJAR) {
        Path path = getPathFromResource(relativePath, insideJAR);
        if (path != null)
            return path.toString();
        Log.error(Resource.class, "Can't get the path from resource: " + relativePath);
        return null;
    }

    public static String getResourcePath(boolean insideJAR) {
        return getResourcePath("", insideJAR);
    }

    public static ImageIcon loadImageIcon(String relativePath, boolean insideJAR, int width, int height) {
        ImageIcon icon = loadImageIcon(relativePath, insideJAR);
        if (icon != null)
            return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT | Image.SCALE_FAST | Image.SCALE_SMOOTH | Image.SCALE_REPLICATE | Image.SCALE_AREA_AVERAGING));
        Log.error(Resource.class, "Can't load the image icon: " + relativePath);
        return null;
    }

    public static ImageIcon loadImageIcon(String relativePath, boolean insideJAR, int width) {
        ImageIcon icon = loadImageIcon(relativePath, insideJAR);
        if (icon != null) {
            double height = icon.getIconHeight() * 1.0 * width / icon.getIconWidth();
            return new ImageIcon(icon.getImage().getScaledInstance(width, (int) height, Image.SCALE_DEFAULT | Image.SCALE_FAST | Image.SCALE_SMOOTH | Image.SCALE_REPLICATE | Image.SCALE_AREA_AVERAGING));
        }
        Log.error(Resource.class, "Can't load the image icon: " + relativePath);
        return null;
    }

    public static ImageIcon loadImageIcon(String relativePath, boolean insideJAR) {
        URL location = getURLFromResource(relativePath, insideJAR);
        if (location != null)
            return new ImageIcon(location);
        Log.error(Resource.class, "Can't get the location: " + relativePath);
        return null;
    }

    public static ImageIcon loadImageIcon(String relativePath, int width, int height) {
        return loadImageIcon(relativePath, true, width, height);
    }

    public static ImageIcon loadImageIcon(String relativePath, int width) {
        return loadImageIcon(relativePath, true, width);
    }

    public static ImageIcon loadImageIcon(String relativePath) {
        return loadImageIcon(relativePath, true);
    }

    public static SmoothIcon loadSmoothIcon(String relativePath, boolean insideJAR, int width, int height) {
        ImageIcon icon = loadImageIcon(relativePath, insideJAR, width, height);
        if (icon != null)
            return new SmoothIcon(icon);
        Log.error(Resource.class, "Can't load the image icon: " + relativePath);
        return null;
    }

    public static SmoothIcon loadSmoothIcon(String relativePath, boolean insideJAR, int width) {
        ImageIcon icon = loadImageIcon(relativePath, insideJAR, width);
        if (icon != null)
            return new SmoothIcon(icon);
        Log.error(Resource.class, "Can't load the image icon: " + relativePath);
        return null;
    }

    public static SmoothIcon loadSmoothIcon(String relativePath, boolean insideJAR) {
        ImageIcon icon = loadImageIcon(relativePath, insideJAR);
        if (icon != null)
            return new SmoothIcon(icon);
        Log.error(Resource.class, "Can't load the image icon: " + relativePath);
        return null;
    }

    public static SmoothIcon loadSmoothIcon(String relativePath, int width, int height) {
        return loadSmoothIcon(relativePath, true, width, height);
    }

    public static SmoothIcon loadSmoothIcon(String relativePath, int width) {
        return loadSmoothIcon(relativePath, true, width);
    }

    public static SmoothIcon loadSmoothIcon(String relativePath) {
        return loadSmoothIcon(relativePath, true);
    }

    public static FlatSVGIcon loadSVGIcon(String relativePath, boolean insideJAR, int width, int height) {
        FlatSVGIcon icon = loadSVGIcon(relativePath, insideJAR);
        if (icon != null)
            return icon.derive(width, height);
        Log.error(Resource.class, "Can't load the SVG icon: " + relativePath);
        return null;
    }

    public static FlatSVGIcon loadSVGIcon(String relativePath, boolean insideJAR, int width) {
        FlatSVGIcon icon = loadSVGIcon(relativePath, insideJAR);
        if (icon != null) {
            double height = icon.getIconHeight() * 1.0 * width / icon.getIconWidth();
            return icon.derive(width, (int) height);
        }
        Log.error(Resource.class, "Can't load the SVG icon: " + relativePath);
        return null;
    }

    public static FlatSVGIcon loadSVGIcon(String relativePath, boolean insideJAR) {
        URL location = getURLFromResource(relativePath, insideJAR);
        if (location != null)
            return new FlatSVGIcon(location);
        Log.error(Resource.class, "Can't get the location: " + relativePath);
        return null;
    }

    public static FlatSVGIcon loadSVGIcon(String relativePath, int width, int height) {
        return loadSVGIcon(relativePath, true, width, height);
    }

    public static FlatSVGIcon loadSVGIcon(String relativePath, int width) {
        return loadSVGIcon(relativePath, true, width);
    }

    public static FlatSVGIcon loadSVGIcon(String relativePath) {
        return loadSVGIcon(relativePath, true);
    }

    public static Properties loadProperties(String relativePath, boolean insideJAR) {
        try (InputStream inputStream = getInputStreamFromResource(relativePath, insideJAR)) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            }
            Log.error(Resource.class, "Can't get the input stream from resource: " + relativePath);
        } catch (IOException e) {
            Log.error(Resource.class, e.toString());
        }
        return null;
    }

    public static Properties loadProperties(String relativePath) {
        return loadProperties(relativePath, true);
    }

    public static void copyResource(String sourceRelativePath, String destinationRelativePath, boolean overwrite) {
        Path destinationPath = getPathFromResource(destinationRelativePath, false);
        if (destinationPath == null) {
            Log.error(Resource.class, "Destination path is not specified: " + destinationRelativePath);
            return;
        }
        if (Files.notExists(destinationPath) || overwrite) {
            try (InputStream inputStream = getInputStreamFromResource(sourceRelativePath, true)) {
                if (inputStream != null) {
                    setReadOnly(destinationPath, false);
                    Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    setReadOnly(destinationPath, true);
                } else
                    Log.error(Resource.class, "Resource not found: " + sourceRelativePath);
            } catch (IOException e) {
                Log.error(Resource.class, e.toString());
            }
        }
    }

    public static void copyResource(String sourceRelativePath, String destinationRelativePath) {
        copyResource(sourceRelativePath, destinationRelativePath, false);
    }

    public static boolean isReadOnly(Path filePath) {
        return Files.isRegularFile(filePath) && !Files.isWritable(filePath);
    }

    public static void setReadOnly(Path filePath, boolean makeReadOnly) {
        if (isReadOnly(filePath) == makeReadOnly)
            return;
        boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
        try {
            if (isPosix) {
                Set<PosixFilePermission> permissions = new HashSet<>();
                permissions.add(PosixFilePermission.OWNER_READ);

                if (makeReadOnly) {
                    Files.setPosixFilePermissions(filePath, permissions);
                } else {
                    permissions.add(PosixFilePermission.OWNER_WRITE);
                    Files.setPosixFilePermissions(filePath, permissions);
                }
            } else {
                DosFileAttributeView dosView = Files.getFileAttributeView(filePath, DosFileAttributeView.class);
                if (dosView != null) {
                    dosView.setReadOnly(makeReadOnly);
                } else {
                    Log.error(Resource.class, "DOS/Windows file attribute view not supported for: " + filePath);
                }
            }
        } catch (IOException e) {
            Log.error(Resource.class, "Failed to set file as read-only: " + filePath + " - " + e.getMessage());
        }
    }
}
