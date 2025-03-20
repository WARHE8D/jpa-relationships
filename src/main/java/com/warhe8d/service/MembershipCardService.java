package com.warhe8d.service;

import com.warhe8d.models.MembershipCard;

import java.util.Optional;

public interface MembershipCardService {
    Optional<MembershipCard> createMembershipCard(MembershipCard membershipCard);
    Optional<MembershipCard> getMembershipCardById(long id);
    Optional<MembershipCard> updateMembershipCard(long id,MembershipCard membershipCard);
    int deleteMembershipCard(long id);
}
