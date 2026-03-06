package com.example.Spring.LMS.progresses;

import java.util.List;

public interface ProgressService {

    void completeLesson(Long id, Long userId);

    List<ProgressToResponse> getProgress(Long id);
}