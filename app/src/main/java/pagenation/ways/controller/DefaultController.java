package pagenation.ways.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pagenation.ways.entiry.Story;
import pagenation.ways.service.DataService;

@RestController
public class DefaultController {
    @Autowired
    private DataService dataService;

    @GetMapping("/page")
    public List<Story> page(@RequestParam("page") int page, @RequestParam("size") int size) {
        return dataService.page(page, size);
    }

    @GetMapping("/page_stream")
    public Stream<Story> pageStream(@RequestParam("page") int page, @RequestParam("size") int size) {
        return dataService.pageStream(page, size);
    }

    @GetMapping("/raw_page_stream")
    public Stream<Story> rawPageStream(@RequestParam("page") int page, @RequestParam("size") int size) {
        return dataService.rawPageStream(page, size);
    }

    @GetMapping("/em_page_stream")
    public Stream<Story> emPageStream(@RequestParam("page") int page, @RequestParam("size") int size) {
        return dataService.emPageStream(page, size);
    }

    @GetMapping("/repo_page_stream")
    public Stream<Story> repoPageStream(@RequestParam("page") int page, @RequestParam("size") int size) {
        return dataService.repoPageStream(page, size);
    }
}
