package io.napadlek.graphapi;

import io.napadlek.graphapi.business.data.LocationDO;
import io.napadlek.graphapi.business.data.PlaceInfoDO;
import io.napadlek.graphapi.business.data.PlaceSearchResultDO;
import io.napadlek.graphapi.business.service.PlaceLocationDO;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ConnectionRepository connectionRepository;
    @MockBean
    private Connection<Facebook> fbConnection;
    @MockBean
    private GraphApi graphApi;

    @Captor
    private ArgumentCaptor<MultiValueMap<String, String>> searchParamsCaptor;

    @Before
    public void setUp() {
        given(connectionRepository.findPrimaryConnection(Facebook.class)).willReturn(fbConnection);
    }


    @Test
    public void shouldRespondToSimpleQuery() {
        // given
        String testedPath = "/poland/poznan/place";
        PlaceSearchResultDO mockSearchResult = prepareMockSearchResult();
        given(graphApi.fetchObject(eq("search"), eq(PlaceSearchResultDO.class), any(MultiValueMap.class))).willReturn(mockSearchResult);
        // when
        PlaceLocationDO restResult = restTemplate.getForEntity(testedPath, PlaceLocationDO.class).getBody();
        // then
        assertThat(restResult).isNotNull();
        assertThat(restResult.getName()).isEqualTo("Found place");
        assertThat(restResult.getLatitude()).isEqualTo(123.44d, Offset.offset(0.00001d));
        assertThat(restResult.getLongitude()).isEqualTo(123.441d, Offset.offset(0.00001d));
        verify(graphApi).fetchObject(eq("search"), eq(PlaceSearchResultDO.class), searchParamsCaptor.capture());
        MultiValueMap<String, String> searchParams = searchParamsCaptor.getValue();
        assertThat(searchParams).containsKey("type").containsKey("q").containsKey("limit").containsKey("fields");
        assertThat(searchParams.getFirst("type")).isEqualTo("place");
        assertThat(searchParams.getFirst("q")).isEqualTo("place");
    }


    private PlaceSearchResultDO prepareMockSearchResult() {
        PlaceSearchResultDO mockSearchResult = new PlaceSearchResultDO();
        mockSearchResult.setData(Arrays.asList(new PlaceInfoDO() {{
            setId("12345");
            setName("Found place");
            setLocation(new LocationDO() {{
                setCity("Poznan");
                setCountry("Poland");
                setLatitude(123.44d);
                setLongitude(123.441d);
            }});
        }}, new PlaceInfoDO() {{
            setId("12346");
            setName("Found place 2");
            setLocation(new LocationDO() {{
                setCity("Warszawa");
                setCountry("Poland");
                setLatitude(223.44d);
                setLongitude(223.441d);
            }});
        }}));
        return mockSearchResult;
    }

}
