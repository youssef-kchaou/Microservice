package com.example.courzeloproject.Service;

import com.example.courzeloproject.dto.MailDto;

public interface EmailSender {
    String sendEmail( MailDto email);
}
