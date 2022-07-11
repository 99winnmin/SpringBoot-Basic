package objectMapperDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.util.Arrays;
import java.util.List;

public class ObjectMapperTest {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("om");

        ObjectMapper objectMapper = new ObjectMapper();


        User user = new User();
        user.setName("유승민");
        user.setAge(24);

        Car car1 = new Car();
        car1.setName("audi");
        car1.setCarNumber("11가 1234");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 4321");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

//        System.out.println(user);

        // json 직렬화
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // json 직렬화 결과 출력
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        // object는 문자열 받듯이 받으면 안됨
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;

        // object를 받아서 원하는 타입으로 변환시켜줌, json 역직렬화
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        // json value값 바꾸기
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name","steve");
        objectNode.put("age",25);

        // json 예쁘기 출력하기기
        System.out.println(objectNode.toPrettyString());
    }
}
