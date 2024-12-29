package com.walidd.Hsabati.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walidd.Hsabati.DTO.GraphDto;
import com.walidd.Hsabati.Service.Graph.GraphSrv;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/graph")
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class GraphController {

    private final GraphSrv graphSrv;

    @RequestMapping("/data")
    public ResponseEntity<GraphDto> getGraphData(){
        return ResponseEntity.ok(graphSrv.getGraphData());
    }

    @RequestMapping("/stats")
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(graphSrv.getStats());
    }

}
