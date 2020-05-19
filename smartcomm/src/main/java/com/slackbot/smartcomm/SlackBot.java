package com.slackbot.smartcomm;


import java.util.regex.*;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.regex.Matcher;

@JBot
@Profile("slack")
@Component
public class SlackBot extends Bot
{

    private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);
    @Value("${slackBotToken}")
    private String slackToken;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Controller(events = EventType.DIRECT_MESSAGE)
    public void onReceiveDM(WebSocketSession session, Event event)
    {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(event.getText());
        System.out.println(event.getText());
        matcher.find();
        reply(session, event, "Hey, I'll check about "+matcher.group()+" and get back to you");
    }

    @Controller(events = EventType.DIRECT_MENTION)
    public void onReceiveMessage(WebSocketSession session, Event event)
    {
        String res = "";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(event.getText());
        System.out.println(event.getText());
        while(matcher.find())
        {
            res = res.concat(matcher.group());
        }
        System.out.println(res);
        reply(session, event, "Hey, I'll check about "+res.substring(4)+" and get back to you");
    }

}
