package com.example.week42;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index")
public class MathView extends FormLayout {
    private Button plus, minus, multi, divide, mod, max;
    private TextField number1, number2, answer;
    public MathView(){
        VerticalLayout value = new VerticalLayout();
        HorizontalLayout math = new HorizontalLayout();
        number1 = new TextField();
        number2 = new TextField();
        answer = new TextField();
        number1.setLabel("number1");
        number1.setPlaceholder("Please Input");
        number2.setLabel("number2");
        number2.setPlaceholder("Please Input");
        answer.setLabel("answer");
        answer.setPlaceholder("answer");
        answer.setEnabled(false);
        plus = new Button("+");
        minus = new Button("-");
        multi = new Button("x");
        divide = new Button("/");
        mod = new Button("mod");
        max = new Button("max");
        math.add(plus, minus, multi, divide, mod, max);
        value.add(number1, number2, math, answer);
        add(value);
        plus.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());
            String response = WebClient.create().get().uri("http://localhost:8080/plus/"+num1+"/"+num2).retrieve().bodyToMono(String.class).block();
            answer.setValue(response);
        });
        minus.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());
            String response = WebClient.create().get().uri("http://localhost:8080/minus/"+num1+"/"+num2).retrieve().bodyToMono(String.class).block();
            answer.setValue(response);
        });
        multi.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());
            String response = WebClient.create().get().uri("http://localhost:8080/multi/"+num1+"/"+num2).retrieve().bodyToMono(String.class).block();
            answer.setValue(response);
        });
        divide.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());
            String response = WebClient.create().get().uri("http://localhost:8080/divide/"+num1+"/"+num2).retrieve().bodyToMono(String.class).block();
            answer.setValue(response);
        });
        mod.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());
            String response = WebClient.create().get().uri("http://localhost:8080/mod/"+num1+"/"+num2).retrieve().bodyToMono(String.class).block();
            answer.setValue(response);
        });
        max.addClickListener(event ->{
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", number1.getValue()); // key คือ n1, value คือ n1.getValue())
            formData.add("n2", number2.getValue());
            String response = WebClient.create().post().uri("http://localhost:8080/max/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
            answer.setValue(response);
        });
    }
}
