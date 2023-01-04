package vanille.batch.job.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import vanille.batch.repository.notification.NotificationEntity;
import vanille.batch.repository.notification.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class SendNotificationItemWriter implements ItemWriter<NotificationEntity> {

    private final NotificationRepository notificationRepository;

    private final KakaoTalkMessageAdapter kakaoTalkMessageAdapter;

    public SendNotificationItemWriter(NotificationRepository notificationRepository, KakaoTalkMessageAdapter kakaoTalkMessageAdapter) {
        this.notificationRepository = notificationRepository;
        this.kakaoTalkMessageAdapter = kakaoTalkMessageAdapter;
    }

    @Override
    public void write(List<? extends NotificationEntity> items) throws Exception {
        int count = 0;

        for (NotificationEntity item : items) {
            boolean successful = kakaoTalkMessageAdapter.sendKakaoTalkMessage(item.getUuid(), item.getText());

            if(successful) {
                item.setSent(true);
                item.setSentAt(LocalDateTime.now());
                notificationRepository.save(item);
                count++;
            }
        }
        log.info("sendNotificationItemWriter - write: 수업 전 알람 {}/{}건 전송 성공", count, items.size());
    }
}
