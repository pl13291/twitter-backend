package challenge.controllers;

import challenge.dtos.BasicUserDTO;
import challenge.dtos.MessageDTO;
import challenge.facades.MessageFacade;
import challenge.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/users/{userHandle}")
public class UserRestController {

    @Autowired
    private MessageFacade messageFacade;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/messages", produces = {"application/json"})
    @ResponseBody
    @PreAuthorize("#userHandle == authentication.name")
    public List<MessageDTO> getMessages(@PathVariable("userHandle") String userHandle,
                                        @RequestParam(value="search", defaultValue="") String search) {
        return messageFacade.getMessages(userHandle, search);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/followers", produces = {"application/json"})
    @ResponseBody
    public List<BasicUserDTO> getFollowers(@PathVariable("userHandle") String userHandle) {
        return userFacade.getFollowers(userHandle);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/following", produces = {"application/json"})
    @ResponseBody
    public List<BasicUserDTO> getFollowing(@PathVariable("userHandle") String userHandle) {
        return userFacade.getFollowing(userHandle);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/follow", produces = {"application/json"})
    @ResponseBody
    @PreAuthorize("#userHandle != authentication.name")
    public Integer followUser(@PathVariable("userHandle") String userHandle) {
        return userFacade.followUser(userHandle);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unfollow", produces = {"application/json"})
    @ResponseBody
    @PreAuthorize("#userHandle != authentication.name")
    public Integer unFollowUser(@PathVariable("userHandle") String userHandle) {
        return userFacade.unFollowUser(userHandle);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shortestDistance", produces = {"application/json"})
    @ResponseBody
    @PreAuthorize("#userHandle != authentication.name")
    public Integer getShortestDistance(@PathVariable("userHandle") String userHandle) {
        return userFacade.getShortestDistance(userHandle);
    }
}


