package com.hashan.nagp.workerservice.services.impl;

import com.hashan.nagp.workerservice.models.WorkerModel;
import com.hashan.nagp.workerservice.services.WorkerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WorkerServiceImpl implements WorkerService {
    private static final Map<String, WorkerModel> WORKER_LIST = new HashMap<>();

    static {
        WorkerModel workerElectrician = new WorkerModel();
        workerElectrician.setId("W001");
        workerElectrician.setUtilityId("001");
        workerElectrician.setName("Jhon Doe");
        workerElectrician.setTelephone("005468541");
        WORKER_LIST.put(workerElectrician.getId(), workerElectrician);
    }

    @Override
    public List<WorkerModel> getAllWorkers() {
        return new ArrayList<>(WORKER_LIST.values());
    }

    @Override
    public WorkerModel getWorkerById(String id) {
        return WORKER_LIST.get(id);
    }

    @Override
    public WorkerModel addWorker(WorkerModel workerModel) {
        WORKER_LIST.put(workerModel.getId(), workerModel);
        return workerModel;
    }

    @Override
    public WorkerModel updateWorker(WorkerModel workerModel) {
        WORKER_LIST.put(workerModel.getId(), workerModel);
        return workerModel;
    }

    @Override
    public void deleteWorker(String id) {
        WORKER_LIST.remove(id);
    }
}
