package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel, allowing for saving, loading, and importing hotel data.
 **/
public class HotelManager {
  /** The current zoo hotel */
  private Hotel _hotel = new Hotel();
  private String _filename = "";
  
  /**
   * Saves the serialized application's state into the file associated with the current network.
   *
   * @throws FileNotFoundException if the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file associated.
   * @throws IOException if there is an error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_filename.equals("")) {
      throw new MissingFileAssociationException();
    } else {
      saveObject(_filename, _hotel);
      _hotel.saveState();
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated with this file.
   *
   * @param filename the name of the file to save the state into.
   * @throws FileNotFoundException if the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file associated.
   * @throws IOException if there is an error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    saveObject(filename, _hotel);
    _filename = filename;
    _hotel.saveState();
  }
  
  /**
   * Loads the serialized application's state from the specified file.
   *
   * @param filename name of the file containing the serialized application's state to load.
   * @throws UnavailableFileException if the specified file does not exist or there is an error while processing the file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try {
      _hotel = (Hotel) readObject(filename);
      _filename = filename;
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
  }
  
  /**
   * Reads a text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entities represented in the import file.
   *
   * @param filename name of the text input file.
   * @throws ImportFileException if an error occurs during the processing of the import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      // Import file unsaves the hotel
      _hotel.unsaveState();
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel.
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  /**
   * Creates a new empty hotel, resetting the current hotel state.
   */
  public void newHotel() {
    _hotel = new Hotel();
    // Empty hotel is not savable
    _hotel.saveState();
    _filename = "";
  }

  /**
   * Checks if the current hotel state is saved.
   *
   * @return true if the current hotel state is saved; false otherwise.
   */
  public boolean isSaved() {
    return _hotel.isSaved();
  }

  /**
   * Checks if there is a file associated with the current hotel state.
   *
   * @return true if there is a file associated; false otherwise.
   */
  public boolean hasFileAssociated() {
    return !_filename.equals("");
  }

  /**
   * Saves an object to the specified file.
   *
   * @param file the name of the file to save the object into.
   * @param obj the object to be saved.
   * @throws IOException if an I/O error occurs while writing to the file.
   * @throws FileNotFoundException if the file cannot be created or opened.
   */
  public void saveObject(String file, Object obj) throws IOException, FileNotFoundException {
    try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(file))) {
      obOut.writeObject(obj);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
  }

  /**
   * Reads an object from the specified input file.
   *
   * @param inputFilename the name of the file to read the object from.
   * @return the object read from the file.
   * @throws IOException if an I/O error occurs while reading from the file.
   * @throws ClassNotFoundException if the class of a serialized object cannot be found.
   */
  public Object readObject(String inputFilename) throws IOException, ClassNotFoundException {
    ObjectInputStream objIn = null;
    try {
      objIn = new ObjectInputStream(new FileInputStream(inputFilename));
      return objIn.readObject();
    } finally {
      if (objIn != null) {
        objIn.close();
      }
    }
  }
}
