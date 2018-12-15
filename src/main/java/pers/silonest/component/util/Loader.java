package pers.silonest.component.util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class Loader {

  /**
   * Compute the number of occurrences a resource can be found by a class loader.
   *
   * @param resource
   * @param classLoader
   * @return
   * @throws IOException
   */

  public static Set<URL> getResources(String resource, ClassLoader classLoader) throws IOException {
    // See LBCLASSIC-159
    Set<URL> urlSet = new HashSet<URL>();
    Enumeration<URL> urlEnum = classLoader.getResources(resource);
    while (urlEnum.hasMoreElements()) {
      URL url = urlEnum.nextElement();
      urlSet.add(url);
    }
    return urlSet;
  }

  /**
   * Search for a resource using the classloader passed as parameter.
   *
   * @param resource the resource name to look for
   * @param classLoader the classloader used for the search
   */
  public static URL getResource(String resource, ClassLoader classLoader) {
    try {
      return classLoader.getResource(resource);
    } catch (Throwable t) {
      return null;
    }
  }

  /**
   * Attempt to find a resource by using the classloader that loaded this class, namely Loader.class.
   *
   * @param resource
   * @return
   */
  public static URL getResourceBySelfClassLoader(String resource) {
    return getResource(resource, getClassLoaderOfClass(Loader.class));
  }

  /**
   * Get the class loader of the object passed as argument. Return the system class loader if
   * appropriate.
   *
   * @param o
   * @return
   */
  public static ClassLoader getClassLoaderOfObject(Object o) {
    if (o == null) {
      throw new NullPointerException("Argument cannot be null");
    }
    return getClassLoaderOfClass(o.getClass());
  }

  /**
   * Return the class loader which loaded the class passed as argument. Return the system class loader
   * if appropriate.
   *
   * @param clazz
   * @return
   */
  public static ClassLoader getClassLoaderOfClass(final Class<?> clazz) {
    ClassLoader cl = clazz.getClassLoader();
    if (cl == null) {
      return ClassLoader.getSystemClassLoader();
    } else {
      return cl;
    }
  }

}
