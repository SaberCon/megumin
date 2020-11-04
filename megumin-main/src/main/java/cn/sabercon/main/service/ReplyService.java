package cn.sabercon.main.service;

import cn.sabercon.main.repo.ReplyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepo repo;
}
