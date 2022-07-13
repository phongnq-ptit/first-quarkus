package org.acme;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HandleFile {

  public HandleFile() {
  }

  public static Map<String, Student> read() throws IOException {
    Map<String, Student> map = new HashMap<>();

    File file = new File("data.txt");
    InputStream inputStream = new FileInputStream(file);
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(inputStreamReader);

    ArrayList<String> str = new ArrayList<String>();
    String line;
    while ((line = reader.readLine()) != null) {
      str.add(line);
    }

    for (int i = 0; i < str.size(); i++) {
      String[] parts = str.get(i).split("--");
      Student newStu = new Student();

      newStu.setId(parts[0]);
      newStu.setName(parts[1]);
      newStu.setYear(Integer.parseInt(parts[2]));
      newStu.setRating(parts[3]);

      map.put(newStu.getId(), newStu);
    }

    return map;
  }

  public static void writeV2(String data) throws IOException {
    File file = new File("data.txt");
    OutputStream outputStream = new FileOutputStream(file, true);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

    outputStreamWriter.write(data);
    outputStreamWriter.write("\n");

    outputStreamWriter.flush();
  }

  public static void writeV2(Map<String, Student> map) throws IOException {
    File file = new File("data.txt");
    OutputStream outputStream = new FileOutputStream(file);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

    HandleFile handleFile = new HandleFile();

    Set<String> keySet = map.keySet();
    for (String key : keySet) {
      Student st = map.get(key);
      handleFile.writeV2(st.tostring());
    }

    outputStreamWriter.flush();
  }
}
