package com.warhe8d.service;

import com.warhe8d.models.LibraryMember;
import com.warhe8d.models.MembershipCard;
import com.warhe8d.repo.LibraryMemberRepo;
import com.warhe8d.repo.MembershipCardRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipCardServiceImpl implements MembershipCardService {
    private final MembershipCardRepo membershipCardRepo;
    private final LibraryMemberService libraryMemberService;

    public MembershipCardServiceImpl(MembershipCardRepo membershipCardRepo, LibraryMemberService libraryMemberService) {
        this.membershipCardRepo = membershipCardRepo;
        this.libraryMemberService = libraryMemberService;
    }

    @Override
    public Optional<MembershipCard> createMembershipCard(MembershipCard membershipCard) {
        //save libmem present
        LibraryMember responseLm = membershipCard.getLibraryMember();
        if (responseLm != null && responseLm.getId() != null) {
            LibraryMember savedLm = libraryMemberService.getMemberById(responseLm.getId()).orElse(null);
            if (savedLm != null) {
                libraryMemberService.updateMember(responseLm.getId(), savedLm);
                membershipCard.setLibraryMember(savedLm);
            }
        }
        return Optional.of(membershipCardRepo.save(membershipCard));
    }

    @Override
    public Optional<MembershipCard> getMembershipCardById(long id) {
        return membershipCardRepo.findById(id);
    }

    @Override
    public Optional<MembershipCard> updateMembershipCard(long id, MembershipCard membershipCard) {
        Optional<MembershipCard> mc = membershipCardRepo.findById(id);
        if (mc.isPresent()) {
            MembershipCard updatedCard = mc.get();
            updatedCard.setCardNumber(membershipCard.getCardNumber());
            updatedCard.setExpiryDate(membershipCard.getExpiryDate());
            //1-1 relation save libmem first
            LibraryMember lm = libraryMemberService
                    .getMemberById(membershipCard.getLibraryMember().getId())
                    .orElse(null);
            lm = libraryMemberService.updateMember(membershipCard.getLibraryMember().getId(), lm)
                    .orElse(null);
            updatedCard.setLibraryMember(lm);
            return Optional.of(membershipCardRepo.save(updatedCard));
        }
        return Optional.empty();
    }

    @Override
    public int deleteMembershipCard(long id) {
        Optional<MembershipCard> mc = membershipCardRepo.findById(id);
        if (mc.isPresent()) {
            membershipCardRepo.delete(mc.get());
            return 1;
        }
        return 0;
    }
}
