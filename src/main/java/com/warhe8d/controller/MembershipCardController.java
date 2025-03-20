package com.warhe8d.controller;

import com.warhe8d.models.MembershipCard;
import com.warhe8d.service.MembershipCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lib/card")
public class MembershipCardController {
    private MembershipCardService membershipCardService;

    public MembershipCardController(MembershipCardService membershipCardService) {
        this.membershipCardService = membershipCardService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCard(@RequestBody MembershipCard membershipCard) {
        Optional<MembershipCard> card = membershipCardService.createMembershipCard(membershipCard);
        if(card.isPresent()) {
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Could not create membership card",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable long id) {
        Optional<MembershipCard> card = membershipCardService.getMembershipCardById(id);
        if(card.isPresent()) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        return new ResponseEntity<>("Card not found",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCard(@PathVariable long id, @RequestBody MembershipCard membershipCard) {
        Optional<MembershipCard> card = membershipCardService.updateMembershipCard(id, membershipCard);
        if(card.isPresent()) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not update membership card",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable long id) {
        int deleted = membershipCardService.deleteMembershipCard(id);
        if(deleted==1) {
            return new ResponseEntity<>("Card Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete",HttpStatus.BAD_REQUEST);
    }
}
