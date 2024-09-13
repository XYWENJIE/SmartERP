package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.domain.ResultPage;
import com.benjamin.smarterp.domain.ResultStatus;
import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import jakarta.validation.Valid;
import org.flowable.engine.RuntimeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 人员控制器，
 */
@RestController
@RequestMapping("personnel")
public class PersonnelController {

    private final PersonnelRepository personnelRepository;
    //private final RuntimeService runtimeService;

    public PersonnelController(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    /**
     *
     * @return
     */
    @GetMapping("list")
    public ResultStatus<ResultPage<Personnel>> list(){
        Page<Personnel> personnelPage = this.personnelRepository.findAll((root, query, criteriaBuilder) -> {return null;}, PageRequest.of(0,10));
        return ResultStatus.success(new ResultPage<>(personnelPage));
    }

    /**
     * 创建人员信息
     * @return
     */
    @PostMapping
    public ResultStatus<String> create(@RequestBody @Valid Personnel personnel){
        //this.personnelRepository.save(personnel);
        //this.runtimeService.startProcessInstanceByKey("Employee");
        return ResultStatus.success("创建人员信息");
    }

    /**
     * 获取单个ID编号
     * @param id id
     * @return
     */
    @GetMapping("{id}")
    public ResultStatus<Personnel> getPersonnel(@PathVariable("id") String id){
        Optional<Personnel> optional = this.personnelRepository.findById(id);
        return optional.map(ResultStatus::success).orElseGet(() -> ResultStatus.error(""));
    }

}
