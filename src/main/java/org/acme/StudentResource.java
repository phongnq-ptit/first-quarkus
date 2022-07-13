package org.acme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/studentz")
public class StudentResource {

  public static Map<String, Student> map = new HashMap<>();


  public StudentResource() {
    HandleFile hFile = new HandleFile();
    try {
      map = hFile.read();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getAllStudent() {
    List<String> students = new ArrayList<>();

    Set<String> keySet = map.keySet();
    for (String key : keySet) {
      Student st = map.get(key);
      students.add(st.tostring());
    }

    return Response.ok(students).build();
  }

//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  public Response getStudent() {
//    return Response.ok(students).build();
//  }
//
//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  @Path("/size")
//  public Integer amountStudent() {
//    return students.size();
//  }
//
//  @POST
//  @Produces(MediaType.TEXT_PLAIN)
//  @Consumes(MediaType.TEXT_PLAIN)
//  public Response createStudent(String student) {
//    students.add(student);
//    return Response.ok(students).build();
//  }
//
//  @PUT
//  @Path("{studentUpdate}")
//  @Produces(MediaType.TEXT_PLAIN)
//  @Consumes(MediaType.TEXT_PLAIN)
//  public Response updateStudent(
//      @PathParam("studentUpdate") String studentUpdate,
//      @QueryParam("student") String student
//  ) {
//    students = students.stream().map(item -> {
//      if (item.equalsIgnoreCase(studentUpdate)) {
//        return student;
//      }
//
//      return item;
//    }).collect(Collectors.toList());
//
//    return Response.ok(students).build();
//  }
}
