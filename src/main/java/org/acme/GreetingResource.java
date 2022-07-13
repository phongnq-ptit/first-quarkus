package org.acme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
public class GreetingResource {

  public static ArrayList<Student> students = new ArrayList<>();
  public static ArrayList<String> listStudent = new ArrayList<>();
  public static Map<String, Student> map = new HashMap<>();

  public File urlPath() throws URISyntaxException {
    URL resource = getClass().getClassLoader().getResource("data.txt");
    if (resource == null) {
      throw new IllegalArgumentException("file not found!");
    } else {
      return new File(resource.toURI());
    }
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getAllStudent() throws IOException {
    ArrayList<String> listStudentTmp = new ArrayList<>();
    ArrayList<Student> studentsTmp = new ArrayList<>();
    // Xu ly file
    InputStream is = getClass().getClassLoader().getResourceAsStream("data.txt");
    if (is == null) {
      throw new IllegalArgumentException("file not found!");
    } else {

      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("--");
        Student newStu = new Student();

        newStu.setId(parts[0]);
        newStu.setName(parts[1]);
        newStu.setYear(Integer.parseInt(parts[2]));
        newStu.setRating(parts[3]);

        studentsTmp.add(newStu);
        map.put(newStu.getId(), newStu);

      }
//      is.close();
//      br.close();
    }

    for (Student item : studentsTmp) {
      listStudentTmp.add(item.tostring());
    }

    if (listStudentTmp.size() == 0) {
      return Response.ok("List empty!!").build();
    }

//    is.close();
    return Response.ok(listStudentTmp).build();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  public Response getById(@PathParam("id") String id) throws IOException {
    // reset
    listStudent.clear();
    map.clear();

    // Xu ly file
    InputStream is = getClass().getClassLoader().getResourceAsStream("data.txt");
    if (is == null) {
      throw new IllegalArgumentException("file not found!");
    } else {
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("--");
        Student newStu = new Student();

        newStu.setId(parts[0]);
        newStu.setName(parts[1]);
        newStu.setYear(Integer.parseInt(parts[2]));
        newStu.setRating(parts[3]);

        students.add(newStu);
        map.put(newStu.getId(), newStu);
      }
    }

    for (Student student : students) {
      listStudent.add(student.tostring());
    }

    // solve
    if (map.get(id) == null) {
      return Response.ok("Id does not exist!!").build();
    }

    String student = map.get(id).tostring();

    return Response.ok(student).build();
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  public Response createStudent(String student) throws IOException, URISyntaxException {
    // reset
    listStudent.clear();
    map.clear();

    // Xu ly file
    InputStream is = getClass().getClassLoader().getResourceAsStream("data.txt");
    if (is == null) {
      throw new IllegalArgumentException("file not found!");
    } else {
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("--");
        Student newStu = new Student();

        newStu.setId(parts[0]);
        newStu.setName(parts[1]);
        newStu.setYear(Integer.parseInt(parts[2]));
        newStu.setRating(parts[3]);

        students.add(newStu);
        map.put(newStu.getId(), newStu);
      }

      is.close();
      br.close();
    }

    // solve
//    File file = urlPath();
//    OutputStream outputStream = new FileOutputStream(file, true);
//    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//
//    outputStreamWriter.write(student);
//    outputStreamWriter.write("\n");
//
//    outputStreamWriter.flush();

    String[] parts = student.split("--");
    Student newStu = new Student();

    newStu.setId(parts[0]);
    newStu.setName(parts[1]);
    newStu.setYear(Integer.parseInt(parts[2]));
    newStu.setRating(parts[3]);

    students.add(newStu);
    listStudent.add(student);
    map.put(newStu.getId(), newStu);

    return Response.ok(listStudent).build();
  }
}
