package org.acme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getAllStudent() throws IOException {
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

    if (listStudent.size() == 0) {
      return Response.ok("List empty!!").build();
    }
    return Response.ok(listStudent).build();
  }
}
