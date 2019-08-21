package Week7PA;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class WebServerS3 {
	private final static int LISTENING_PORT = 50510;
	private static final String ROOT_DIRECTORY = "/users/michael.genenko/idea projects/cs1103/src/";

	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		} catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
				// Create a new thread and then I run it
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}

	}

	/**
	 * handleConnection it is used to manage all connections This returns the error
	 * to the client (if present) or returns the requested index or the file request
	 * 
	 * @param connection
	 */
	private static void handleConnection(Socket connection) {
		try {
			OutputStream socketOut = connection.getOutputStream();
			Scanner in = new Scanner(connection.getInputStream());
			// If there is nothing to read --> Error 400
			if (!in.hasNextLine()) {
				sendErrorResponse(400, socketOut);
				throw new Exception("Code 010");
			}
			String line = in.nextLine();
			// If you read something of length 0 --> Error 400
			if (line.trim().length() == 0) {
				sendErrorResponse(400, socketOut);
				throw new Exception("Code 020");
			}

			// If the 3 required parameters are not present --> Error 400
			String[] firstRow = line.split(" ");
			if (firstRow.length != 3) {
				sendErrorResponse(400, socketOut);
				throw new Exception("Code 030");
			}
			// If the request is different from "GET" --> Error 501
			if (!firstRow[0].equals("GET")) {
				sendErrorResponse(501, socketOut);
				throw new Exception("Code 040");
			}
			// If the following parameters do not match --> Error 400
			if (firstRow[1].isEmpty() || (!firstRow[2].equals("HTTP/1.1") && !firstRow[2].equals("HTTP/1.0"))) {
				sendErrorResponse(400, socketOut);
				throw new Exception("Code 050");
			}

			File f = new File(ROOT_DIRECTORY + firstRow[1]);
			// If the file does not exist --> Error 404
			if (!f.exists()) {
				sendErrorResponse(404, socketOut);
				throw new Exception("Code 060");
			}

			if (f.isDirectory()) {
				// If the file is a directory --> Send the index
				sendIndexResponse(f, socketOut);
			} else {
				// If the file cannot be read and it isn't a directory --> Error 403
				if (!f.canRead() || f.length() <= 0) {
					sendErrorResponse(403, socketOut);
					throw new Exception("Code 070");

				}
				// If all ok
				sendOkResponse(f, socketOut);
			}

		} catch (Exception e) {
			// If I make a generic error --> Error 500
			if (!e.getMessage().startsWith("Code"))
				try {
					sendErrorResponse(500, connection.getOutputStream());
				} catch (Exception e2) {
				}
			System.out.println("Error while communicating with client: " + e);
		} finally { // make SURE connection is closed before returning!
			try {
				connection.close();
			} catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}

	/**
	 * Used to send a positive reply and the requested file
	 * 
	 * @param f
	 * @param socketOut
	 */
	private static void sendOkResponse(File f, OutputStream socketOut) {
		// I prepare the response
		ArrayList<String> listOfRow = new ArrayList<String>();
		listOfRow.add("HTTP/1.1 200 OK");
		listOfRow.add("Connection: close");
		listOfRow.add("Content-Type: " + getMimeType(f.getName()));
		listOfRow.add("Content-Length: " + f.length());
		listOfRow.add("");
		// I start to send the response
		sendBrowserResponse(socketOut, listOfRow);
		// If some go wrong I catch the error and I exit
		try {
			// I Send the request file
			sendFile(f, socketOut);
		} catch (Exception e) {
			System.out.println("Error while communicating with client in Ok response: " + e);
		}
	}

	/**
	 * Used to send a positive reply if the requested file is a directory (I display
	 * everything that is present under the directory)
	 * 
	 * @param f
	 * @param socketOut
	 */
	private static void sendIndexResponse(File f, OutputStream socketOut) {
		// Get the list of file and directory
		TreeSet<File> listOfFile = getListOfFile(f);
		// I prepare the response
		ArrayList<String> listOfRow = new ArrayList<String>();
		listOfRow.add("HTTP/1.1 200 OK");
		listOfRow.add("Connection: close");
		listOfRow.add("Content-Type: text/html");
		listOfRow.add("");
		listOfRow.add("<!DOCTYPE html>");
		listOfRow.add("<html>");
		listOfRow.add("<head>");
		listOfRow.add("<meta charset=\"ISO-8859-1\">");
		listOfRow.add("<title>My index</title>");
		listOfRow.add("</head>");
		listOfRow.add("<body>");
		if (listOfFile.isEmpty())
			listOfRow.add("<p>No index in this directory</p>");
		else {
			listOfRow.add("<p>Select an index under directory \""
					+ f.getAbsolutePath().substring(ROOT_DIRECTORY.length()) + "\": </p>");
			for (File file : listOfFile) {
				listOfRow.add("<a href=\"" + file.getAbsolutePath().substring(ROOT_DIRECTORY.length()) + "\">"
						+ file.getName() + "</a><br>");
			}
		}
		listOfRow.add("</body>");
		listOfRow.add("</html>");
		listOfRow.add("");

		// I send the response
		sendBrowserResponse(socketOut, listOfRow);
	}

	/**
	 * Used to send any negative answer
	 * 
	 * @param errorCode
	 * @param socketOut
	 */
	private static void sendErrorResponse(int errorCode, OutputStream socketOut) {
		ArrayList<String> listOfRow = new ArrayList<String>();
		if (errorCode == 400) {
			// Error 400 -> Bad Request
			listOfRow.add("HTTP/1.1 400 Bad Request");
			listOfRow.add("Connection: close");
			listOfRow.add("Content-Type: text/html");
			listOfRow.add("");
			listOfRow.add("<html><head><title>Error</title></head><body>");
			listOfRow.add("<h2>Error: 400 Bad Request</h2>");
			listOfRow.add("<p>Your browser sent a request that this server could not understand.</p>");
			listOfRow.add("</body></html>");
			listOfRow.add("");
		} else if (errorCode == 403) {
			// Error 403 -> Forbidden
			listOfRow.add("HTTP/1.1 403 Forbidden");
			listOfRow.add("Connection: close");
			listOfRow.add("Content-Type: text/html");
			listOfRow.add("");
			listOfRow.add("<html><head><title>Error</title></head><body>");
			listOfRow.add("<h2>Error:  403 Forbidden</h2>");
			listOfRow.add("<p>You don't have permission to read the file.</p>");
			listOfRow.add("</body></html>");
			listOfRow.add("");
		} else if (errorCode == 404) {
			// Error 404 -> Not Found
			listOfRow.add("HTTP/1.1 404 Not Found");
			listOfRow.add("Connection: close");
			listOfRow.add("Content-Type: text/html");
			listOfRow.add("");
			listOfRow.add("<html><head><title>Error</title></head><body>");
			listOfRow.add("<h2>Error:  404 Not Found</h2>");
			listOfRow.add("<p>The resource that you requested does not exist on this server.</p>");
			listOfRow.add("</body></html>");
			listOfRow.add("");
		} else if (errorCode == 500) {
			// Error 500 -> Internal Server Error
			listOfRow.add("HTTP/1.1 500 Internal Server Error");
			listOfRow.add("Connection: close");
			listOfRow.add("Content-Type: text/html");
			listOfRow.add("");
			listOfRow.add("<html><head><title>Error</title></head><body>");
			listOfRow.add("<h2>Error: 500 Internal Server Error</h2>");
			listOfRow.add("<p>Generic Error</p>");
			listOfRow.add("</body></html>");
			listOfRow.add("");
		} else if (errorCode == 501) {
			// Error 501 -> Not Implemented
			listOfRow.add("HTTP/1.1 501 Not Implemented");
			listOfRow.add("Connection: close");
			listOfRow.add("Content-Type: text/html");
			listOfRow.add("");
			listOfRow.add("<html><head><title>Error</title></head><body>");
			listOfRow.add("<h2>Error: 501 Not Implemented</h2>");
			listOfRow.add("<p>Your browser sent a request that this server could not implemented.</p>");
			listOfRow.add("</body></html>");
			listOfRow.add("");
		}
		// I send the response
		sendBrowserResponse(socketOut, listOfRow);
	}

	/**
	 * Used to send any input response
	 * 
	 * @param socketOut
	 * @param listOfRow
	 */
	private static void sendBrowserResponse(OutputStream socketOut, ArrayList<String> listOfRow) {
		// I prepare the Print Writer
		PrintWriter pWriter = new PrintWriter(socketOut);
		// I send all row
		for (String string : listOfRow) 
			pWriter.print(string.trim() + "\r\n");
		
		// I flush the PrintWriter
		pWriter.flush();
	}

	/**
	 * Used to send any input file
	 * 
	 * @param file
	 * @param socketOut
	 * @throws IOException
	 */
	private static void sendFile(File file, OutputStream socketOut) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(socketOut);
		while (true) {
			int x = in.read(); // read one byte from file
			if (x < 0)
				break; // end of file reached
			out.write(x); // write the byte to the socket
		}
		out.flush();
	}

	/**
	 * Used to recover the type of file I'm sending
	 * 
	 * @param fileName
	 * @return
	 */
	private static String getMimeType(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) // no file extension in name
			return "x-application/x-unknown";
		String ext = fileName.substring(pos + 1).toLowerCase();
		if (ext.equals("txt"))
			return "text/plain";
		else if (ext.equals("html"))
			return "text/html";
		else if (ext.equals("htm"))
			return "text/html";
		else if (ext.equals("css"))
			return "text/css";
		else if (ext.equals("js"))
			return "text/javascript";
		else if (ext.equals("java"))
			return "text/x-java";
		else if (ext.equals("jpeg"))
			return "image/jpeg";
		else if (ext.equals("jpg"))
			return "image/jpeg";
		else if (ext.equals("png"))
			return "image/png";
		else if (ext.equals("gif"))
			return "image/gif";
		else if (ext.equals("ico"))
			return "image/x-icon";
		else if (ext.equals("class"))
			return "application/java-vm";
		else if (ext.equals("jar"))
			return "application/java-archive";
		else if (ext.equals("zip"))
			return "application/zip";
		else if (ext.equals("xml"))
			return "application/xml";
		else if (ext.equals("xhtml"))
			return "application/xhtml+xml";
		else
			return "x-application/x-unknown";
		// Note: x-application/x-unknown is something made up;
		// it will probably make the browser offer to save the file.
	}

	/**
	 * Get all files in a directory
	 * 
	 * @param dir
	 * @return TreeSet<File>
	 */
	private static TreeSet<File> getListOfFile(File dir) {
		TreeSet<File> listOfFile = new TreeSet<File>();
		for (String nameFile : dir.list())
			listOfFile.add(new File(dir, nameFile));
		
		return listOfFile;
	}

	/**
	 * 
	 * Class ConnectionThread
	 *
	 */
	private static class ConnectionThread extends Thread {
		Socket connection;

		ConnectionThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			handleConnection(connection);
		}
	}

}
