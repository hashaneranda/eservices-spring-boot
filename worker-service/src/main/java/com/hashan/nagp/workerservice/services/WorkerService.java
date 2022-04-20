package com.hashan.nagp.workerservice.services;

import com.hashan.nagp.workerservice.models.WorkerModel;

import java.util.List;


public interface WorkerService {
    List<WorkerModel> getAllWorkers();
    WorkerModel getWorkerById(String id);
    WorkerModel addWorker(WorkerModel workerModel);
    WorkerModel updateWorker(WorkerModel workerModel);
    void deleteWorker(String id);
}
