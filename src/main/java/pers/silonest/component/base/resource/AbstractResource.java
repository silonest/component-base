package pers.silonest.component.base.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import pers.silonest.component.util.Assert;

public class AbstractResource implements Resource {
  /**
   * This implementation checks whether a File can be opened, falling back to whether an InputStream
   * can be opened. This will cover both directories and content resources.
   */
  @Override
  public boolean exists() {
    // Try file existence: can we find the file in the file system?
    try {
      return getFile().exists();
    } catch (IOException ex) {
      // Fall back to stream existence: can we open the stream?
      try {
        InputStream is = getInputStream();
        is.close();
        return true;
      } catch (Throwable isEx) {
        return false;
      }
    }
  }

  /**
   * This implementation always returns {@code true}.
   */
  @Override
  public boolean isReadable() {
    return true;
  }

  /**
   * This implementation always returns {@code false}.
   */
  @Override
  public boolean isOpen() {
    return false;
  }

  /**
   * This implementation throws a FileNotFoundException, assuming that the resource cannot be
   * resolved to a URL.
   */
  @Override
  public URL getURL() throws IOException {
    throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
  }

  /**
   * This implementation builds a URI based on the URL returned by {@link #getURL()}.
   */
  @Override
  public URI getURI() throws IOException {
    URL url = getURL();
    try {
      return ResourceUtils.toURI(url);
    } catch (URISyntaxException ex) {
      throw new IOException("Invalid URI [" + url + "]", ex);
    }
  }

  /**
   * This implementation throws a FileNotFoundException, assuming that the resource cannot be
   * resolved to an absolute file path.
   */
  @Override
  public File getFile() throws IOException {
    throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
  }

  /**
   * This implementation reads the entire InputStream to calculate the content length. Subclasses
   * will almost always be able to provide a more optimal version of this, e.g. checking a File
   * length.
   * 
   * @see #getInputStream()
   * @throws IllegalStateException if {@link #getInputStream()} returns null.
   */
  @Override
  public long contentLength() throws IOException {
    InputStream is = getInputStream();
    Assert.state(is != null, "Resource InputStream must not be null");
    try {
      long size = 0;
      byte[] buf = new byte[255];
      int read;
      while ((read = is.read(buf)) != -1) {
        size += read;
      }
      return size;
    } finally {
      try {
        is.close();
      } catch (IOException ex) {
      }
    }
  }

  /**
   * This implementation checks the timestamp of the underlying File, if available.
   * 
   * @see #getFileForLastModifiedCheck()
   */
  @Override
  public long lastModified() throws IOException {
    long lastModified = getFileForLastModifiedCheck().lastModified();
    if (lastModified == 0L) {
      throw new FileNotFoundException(getDescription() + " cannot be resolved in the file system for resolving its last-modified timestamp");
    }
    return lastModified;
  }

  /**
   * Determine the File to use for timestamp checking.
   * <p>
   * The default implementation delegates to {@link #getFile()}.
   * 
   * @return the File to use for timestamp checking (never {@code null})
   * @throws IOException if the resource cannot be resolved as absolute file path, i.e. if the
   *         resource is not available in a file system
   */
  protected File getFileForLastModifiedCheck() throws IOException {
    return getFile();
  }

  /**
   * This implementation throws a FileNotFoundException, assuming that relative resources cannot be
   * created for this resource.
   */
  @Override
  public Resource createRelative(String relativePath) throws IOException {
    throw new FileNotFoundException("Cannot create a relative resource for " + getDescription());
  }

  /**
   * This implementation always returns {@code null}, assuming that this resource type does not have
   * a filename.
   */
  @Override
  public String getFilename() {
    return null;
  }


  /**
   * This implementation returns the description of this resource.
   * 
   * @see #getDescription()
   */
  @Override
  public String toString() {
    return getDescription();
  }

  /**
   * This implementation compares description strings.
   * 
   * @see #getDescription()
   */
  @Override
  public boolean equals(Object obj) {
    return (obj == this || (obj instanceof Resource && ((Resource) obj).getDescription().equals(getDescription())));
  }

  /**
   * This implementation returns the description's hash code.
   * 
   * @see #getDescription()
   */
  @Override
  public int hashCode() {
    return getDescription().hashCode();
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }
}
