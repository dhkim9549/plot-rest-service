package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.statistics.distribution.NormalDistribution;
import org.apache.commons.statistics.distribution.ParetoDistribution;
import org.apache.commons.statistics.distribution.ContinuousDistribution.Sampler;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class GreetingController {

    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/greeting")
    public HashMap greeting() {
        HashMap<String, List> map = new HashMap<String, List>();

	NormalDistribution nd = NormalDistribution.of(0.0, 1.0);
        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

	List<Double> arrX = nd.createSampler(rng).samples(1000).boxed().toList();
        List<Double> arrY = nd.createSampler(rng).samples(1000).boxed().toList();

	map.put("X", arrX);
        map.put("Y", arrY);

	return map;
    }

    @GetMapping("/plot-pareto")
    public HashMap plotPareto(@RequestParam int num, @RequestParam double shape) {
        HashMap<String, List> map = new HashMap<String, List>();

        ParetoDistribution dist = ParetoDistribution.of(1.0, shape);
        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

        List<Double> arrX = dist.createSampler(rng).samples(num).boxed().toList();

        map.put("X", arrX);

        return map;
    }
}
