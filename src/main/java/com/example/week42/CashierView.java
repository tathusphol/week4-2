package com.example.week42;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index1")
public class CashierView extends VerticalLayout {
    private TextField money, thousand, fivehundred, onehundred, twenty, ten, five, one;
    private Button text;
    private Change Change;

    public CashierView(){
        money = new TextField();
        money.setLabel("เงินทอน");
        thousand = new TextField();
        fivehundred = new TextField();
        onehundred = new TextField();
        twenty = new TextField();
        ten = new TextField();
        five = new TextField();
        one = new TextField();
        text = new Button("คำนวณเงินทอน");
        thousand.setEnabled(false);
        fivehundred.setEnabled(false);
        onehundred.setEnabled(false);
        twenty.setEnabled(false);
        ten.setEnabled(false);
        five.setEnabled(false);
        one.setEnabled(false);
        thousand.setPrefixComponent(new Span("$1000 : "));
        fivehundred.setPrefixComponent(new Span("$500 : "));
        onehundred.setPrefixComponent(new Span("$100 : "));
        twenty.setPrefixComponent(new Span("$20 : "));
        ten.setPrefixComponent(new Span("$10 : "));
        five.setPrefixComponent(new Span("$5 : "));
        one.setPrefixComponent(new Span("$1 : "));
        money.setPrefixComponent(new Span("$ "));
        add(money, text, thousand, fivehundred, onehundred, twenty, ten, five, one);
        text.addClickListener(event -> {
          int n = Integer.parseInt(money.getValue());

            Change = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/"+n)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();

            setMoney();
        });

    }


    public void setMoney() {
        thousand.setValue(Change.getB1000()+"");
        fivehundred.setValue(Change.getB500()+"");
        onehundred.setValue(Change.getB100()+"");
        twenty.setValue(Change.getB20()+"");
        ten.setValue(Change.getB10()+"");
        five.setValue(Change.getB5()+"");
        one.setValue(Change.getB1()+"");
    }
}